const path = require('path');

function resolve(dir){
    return path.join(__dirname, dir)
}

module.exports = {

    devServer: {
        port: 8888,

        proxy: {
            '/api': {
                target: 'http://localhost:8080'
            }
        }
    },

    configureWebpack: {
        resolve: {
            alias: {
                '@': resolve('src')
            }
        }
    },

    pages: {
        'login': {
            entry: ''
        },
        'home': {

        }
    }
};