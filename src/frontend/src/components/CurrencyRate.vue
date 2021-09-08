<template>
    <div>
        <b-container>
            <b-row align-h="center">
                <b-col sm="10">
                    <b-table
                             :items="currencies" :fields="fields" variant="dark"  class="content" caption-top>
                        <template #table-caption>Курсы валют на {{ date }}</template>
                    </b-table>
                </b-col>
            </b-row>

        </b-container>
    </div>
</template>

<script>
    import http from "../../http-common"

    export default {
        name: "currencyRate",
        data() {
            return {
                currencies: [],
                date: "",
                fields: [{key: "name", label: "Валюта"}, {key: "charCode", label: "Код"}, {key: "nominal", label: "Едениц"}, {key: "value", label: "Ставка" }]
            }
        },
        methods: {
            getCurrencyRate() {
                http.get("/currency/getCurrencyRate")
                    .then(result => {
                        if (result.status === 204) {
                            throw new Error(`${result.status} не удалось получить данные курсов валют`);
                        }

                        this.date = result.data.date;
                        this.currencies = result.data.currencies;
                    })
                    .catch(err => {
                        console.log(err);
                    })
            }
        },
        created() {
            this.getCurrencyRate();
        }
    }
</script>

<style scoped>

</style>