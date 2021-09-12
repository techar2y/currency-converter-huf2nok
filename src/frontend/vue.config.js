// vue.config.js

module.exports = {
    devServer: {
        port: 3000,
        proxy: {
            '/api': {
                target: 'http://currency-converter-huf2nok:8080',
                ws: true,
                changeOrigin: true
            }
        }
    }
}