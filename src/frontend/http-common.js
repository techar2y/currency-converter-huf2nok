import axios from "axios";

export default axios.create({
    baseURL: "https://currency-converter-huf2nok.herokuapp.com:8080/api",
    headers: {
        "Content-type": "application/json"
    }
});
