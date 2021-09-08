<template>
    <b-container>
        <b-form v-if="error.length === 0" class="form">
            <b-card bg-variant="light">
                <b-form-group
                        label-cols-lg="10"
                        label="Конвертация Венгерские форинты -> Российские рубли -> Норвежские кроны"
                        label-size="md"
                        label-class="font-weight-bold pt-0"
                        class="mb-0"
                >
                </b-form-group>

                <b-form-group label="Венгерский форинт" label-for="HUF">
                        <b-input-group size="sm" class="mb-2" append="Ft">
                            <b-form-input
                                    type="text"
                                    placeholder="0.00"
                                    name="HUF"
                                    id="HUF"
                                    required
                                    v-model="huf"
                                    @input="validateHUF">
                                >
                            </b-form-input>
                        </b-input-group>
                </b-form-group>

                <b-form-group label="Российский рубль" label-for="RUB">
                    <b-input-group size="sm" class="mb-2" append="₽">
                        <b-form-input
                                type="text"
                                placeholder="0.00"
                                name="RUB"
                                id="RUB"
                                required
                                readonly
                                v-model="rub"
                        >
                        </b-form-input>
                    </b-input-group>
                </b-form-group>


                <b-form-group label="Норвежский крон" label-for="NOK">
                    <b-input-group size="sm" class="mb-2" append="Kr">
                        <b-form-input
                                type="text"
                                placeholder="0.00"
                                name="NOK"
                                id="NOK"
                                required
                                readonly
                                v-model="nok"
                        >
                        </b-form-input>
                    </b-input-group>
                </b-form-group>
            </b-card>
        </b-form>
        <div else>
            {{ error }}
        </div>
    </b-container>
</template>

<script>
    import http from "../../http-common"


    export default {
        name: "CurrencyConverter",
        data() {
            return {
                hufValidationInfo: {invalid: "", value: null},
                huf: null,
                nok: null,
                rub: null,
                HUFinfo: {},
                NOKinfo: {},
                currencies: [],
                baseCurrencyInfo: {hufToOneRub: 0.00, nokToOneRub: 0.00},
                error: ""
            }
        },
        methods: {
            getCurrencyHufNok() {
                http.get("/currency/getCurrencyHufNok")
                    .then(result => {
                        if(result.status === 204) {
                            this.error = new Error(`${result.status} - не удалось получить данные курсов валют`);
                            throw this.error;
                        }
                        this.HUFinfo = result.data.find(x => x.charCode === "HUF");
                        this.NOKinfo = result.data.find(x => x.charCode === "NOK");
                        this.setBaseCurrencyInfo();
                    })
                    .catch(e => {
                        console.log(e);
                    })
            },
            setBaseCurrencyInfo() {
                this.baseCurrencyInfo.hufToOneRub = this.HUFinfo.value / this.HUFinfo.nominal;
                this.baseCurrencyInfo.nokToOneRub = this.NOKinfo.value / this.NOKinfo.nominal;
            },
            convertHufToNok() {
                this.rub = (this.huf * this.baseCurrencyInfo.hufToOneRub).toFixed(4);
                this.nok = (this.rub / this.baseCurrencyInfo.nokToOneRub).toFixed(4);
            },
            validateHUF() {
                try {
                    let reg = /^[.0-9]+$/;
                    if (!reg.test(this.huf)) {
                        this.rub = null;
                        this.nok = null;
                        throw Error("Не верно введена валюта");
                    }

                    this.huf = parseFloat(this.huf);
                    this.convertHufToNok();
                } catch(e) {
                    console.log(e);
                }
            }
        },
        created() {
            this.getCurrencyHufNok();

        }
    }
</script>

<style scoped>
    .form {
        max-width: 500px;
        margin: auto;
    }
</style>