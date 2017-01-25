process.env.NODE_ENV = 'development'

const nodemon = require('nodemon')
const webpack = require('webpack')
const path = require('path')
const config = require('../config/webpack.config.dev')

let nodemonHasStarted = false

webpack(config).watch({
  stats: {chunks: true}
}, function (err, stats) {
  console.log('[webpack:build:server]', stats.toString({
    chunks: false, // Makes the build much quieter
    hash: false,
    version: false,
    colors: true
  }))
  if (!nodemonHasStarted) {
    nodemon({
      script: path.join(config.output.path, config.output.filename),
      watch: config.output.path
    })
    nodemonHasStarted = true

  }
})
