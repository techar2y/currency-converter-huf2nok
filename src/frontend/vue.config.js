// vue.config.js

module.exports = {
    devServer: {
        port: 3000,
        proxy: {
            '/api': {
                target: 'https://currency-converter-huf2nok.herokuapp.com/:8080',
                ws: true,
                changeOrigin: true
            }
        }
    }
}