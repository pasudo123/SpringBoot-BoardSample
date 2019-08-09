const path = require('path');

function resolve(dir) {
    return path.join(__dirname, dir)
}

module.exports = {

    devServer: {
        hot: true,
        open: true,
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

    publicPath: '/',
    outputDir: '../src/main/resources/static',
    assetsDir: 'static',
    pages: {
        login: {
            entry: './src/pages/login/main.js',
            template: 'public/index.html',
            filename: './templates/login.ftl'
        },
        home: {
            entry: './src/pages/home/main.js',
            template: 'public/index.html',
            filename: './templates/home.ftl'
        },
    },
};