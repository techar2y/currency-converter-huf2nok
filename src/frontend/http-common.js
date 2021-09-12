import axios from "axios";

export default axios.create({
    baseURL: "https://currency-converter-huf2nok.herokuapp.com/api",
    headers: {
        "Content-type": "application/json"
    }
});
