// vue.config.js

module.exports = {
    devServer: {
        port: 3000,
        proxy: {
            '/api': {
                target: 'https://currency-converter-huf2nok.herokuapp.com/',
                ws: true,
                changeOrigin: true
            }
        }
    }
}