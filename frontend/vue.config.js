const path = require('path');

function resolve(dir) {
    return path.join(__dirname, dir)
}

module.exports = {

    devServer: {
        port: 8888,

        proxy: {
            '/auth': {
                target: 'http://localhost:8080'
            },
            '/api': {
                target: 'http://localhost:8080'
            },

        }
    },

    configureWebpack: {
        resolve: {
            alias: {
                '@': resolve('src')
            }
        },
    },

    publicPath: '/',
    outputDir: '../src/main/resources/static',
    assetsDir: 'static',
    pages: {
        login: {
            entry: './src/pages/login/main.js',
            template: 'public/index.html',
            filename: './templates/login.ftl',
        },
        article: {
            entry: './src/pages/home/main.js',
            template: 'public/index.html',
            filename: './templates/article.ftl',
        },
    },
};