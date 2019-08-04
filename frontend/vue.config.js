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


    // assetsDir: 'static',


    outputDir: '../src/main/resources/static',
    assetsDir: process.env.NODE_ENV === 'production'
        ? process.env.VUE_ASSET_DIR
        : process.env.VUE_ASSET_DIR,
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