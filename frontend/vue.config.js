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

    chainWebpack: config => {
        config.optimization.splitChunks({
            cacheGroups: {
                login: {
                    name: `chunk-login-vendors`,
                    chunks: chunk => chunk.name === 'login',
                    enforce: true,
                },
                home: {
                    name: `chunk-article-vendors`,
                    chunks: chunk => chunk.name === 'article',
                    enforce: true,
                },
                common: {
                    name: 'chunk-common',
                    chunks: 'initial',
                    minChunks: 2,
                    reuseExistingChunk: true,
                    enforce: true,
                }
            }
        })
    },

    publicPath: '/',
    outputDir: '../src/main/resources/static',
    assetsDir: 'static',
    pages: {
        login: {
            entry: './src/pages/login/main.js',
            template: 'public/index.html',
            filename: './templates/login.ftl',
            chunks: ['chunk-common', 'chunk-login-vendors', 'login']
        },
        article: {
            entry: './src/pages/home/main.js',
            template: 'public/index.html',
            filename: './templates/article.ftl',
            chunks: ['chunk-common', 'chunk-article-vendors', 'article']
        },
    },
};