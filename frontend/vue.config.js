const path = require('path');

function resolve(dir) {
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

    outputDir: '../src/main/resources/static',
    assetsDir: 'static',
    publicPath: process.env.ENV === 'production'
        ? process.env.VUE_APP_BASE_API
        : process.env.VUE_APP_BASE_API,

    pages: {
        login: {
            entry: './src/pages/login/main.js',
            template: 'public/index.html',
            filename: './templates/login.ftl'
        },
        home: {
            entry: './src/pages/home/main.js',
            template: 'public/index.html',
            filename: './templates/index.ftl'
        },
    },
};