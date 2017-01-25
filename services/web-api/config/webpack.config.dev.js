const webpack = require('webpack')
const fs = require('fs')
const paths = require('./paths')

let nodeModules = {}
fs.readdirSync('node_modules')
  .filter(function(x) {
    return ['.bin'].indexOf(x) === -1
  })
  .forEach(function(mod) {
    nodeModules[mod] = 'commonjs ' + mod
  })


module.exports = {
  //devtool: '#eval-source-map',
  devtool: 'sourcemap',
  entry: paths.appSrcIndex,
  target: 'node',
  output: {
    path: paths.appBuild,
    filename: 'bundle.js'
  },
  module: {
    loaders: [
      {
        test: /\.js$/,
        exclude: /node_modules/,
        loader: 'babel-loader',
        query: {
          presets: ['es2015', 'stage-2']
        }
      }
    ]
  },
  externals: nodeModules,
  plugins: [
    new webpack.BannerPlugin('require("source-map-support").install();',
      { raw: true, entryOnly: false })
  ],
}
