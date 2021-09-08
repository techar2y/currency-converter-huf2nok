<template>
    <div id="app">

        <b-navbar variant="dark" type="dark">
            <b-navbar-nav>
                <b-nav-item to="/">
                    Конвертер
                </b-nav-item>
                <b-nav-item to="/currencyRate">
                    Курс валют
                </b-nav-item>
                <b-nav-item to="/about">
                    About
                </b-nav-item>
            </b-navbar-nav>
        </b-navbar>

        <div class="container mt-3">
            <router-view/>
        </div>
    </div>
</template>

<script>


    export default {
        name: "app"
    };
</script>

<style>

</style>
