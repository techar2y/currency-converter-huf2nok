
import Vue from "vue";
import Router from "vue-router";

Vue.use(Router);

let router = new Router({
    mode: "history",
    routes: [

        {
            path: "/currencyRate",
            name: "CurrencyRate",
            component: () => import("./components/CurrencyRate")
        },
        {
            path: "/",
            name: "CurrencyConverter",
            component: () => import("./components/CurrencyConverter")
        },
        {
            path: "/about",
            name: "About",
            component: () => import("./components/About")
        }
    ]

});

export default router;