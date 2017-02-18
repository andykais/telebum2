//var autoprefixer = require('autoprefixer');
//var webpack = require('webpack');
//var CaseSensitivePathsPlugin = require('case-sensitive-paths-webpack-plugin');
//var InterpolateHtmlPlugin = require('react-dev-utils/InterpolateHtmlPlugin');
//var WatchMissingNodeModulesPlugin = require('react-dev-utils/WatchMissingNodeModulesPlugin');
//var path = require('path')


const webpack = require('webpack')
const HtmlWebpackPlugin = require('html-webpack-plugin')
const getClientEnvironment = require('./env')
const paths = require('./paths')

const babelConfig = {
  cacheDirectory: true,
  presets: [
    ['es2015', { 'modules': false }],
    'stage-2',
    'react'
  ],
  plugins: [
    'syntax-async-functions'
    //'react-hot-loader/babel'
  ]
}
const env = getClientEnvironment('')

module.exports = {
  //devtool: 'cheap-module-source-map',
  devtool: 'source-map',
  entry: [
    require.resolve('react-dev-utils/webpackHotDevClient'),
    require.resolve('./polyfills'),
    paths.appIndexJs
  ],
  output: {
    path: paths.appBuild,
    pathinfo: true,
    filename: 'static/js/bundle.js',
    publicPath: '/'
  },
  resolve: {
    extensions: ['.js', '.json', '.jsx'],
    alias: {
      components: paths.appComponents,
      containers: paths.appContainers,
      router: paths.appRouter,
    }
  },
  module: {
    rules: [
      {
        test: /\.js$/,
        exclude: /node_modules/,
        enforce: 'pre',
        loader: 'eslint-loader',
      },
      {
        test: /\.js$/,
        include: paths.appSrc,
        loader: 'babel-loader',
        options: babelConfig
      },
      //{
        //test: /\.json$/,
        //exclude: [
          //paths.appRoutes
        //],
        //loader: 'json-loader',
      //},
      //{
        //test: /\.json$/,
        //include: [
          //paths.appRoutes
        //],
        //use: [
          //{
            //loader: 'babel-loader',
            //options: babelConfig,
          //},
          //{
            //loader: paths.routesLoader
          //},
        //],
      //},

      {
        exclude: [
          /\.html$/,
          /\.js$/,
          /\.css$/,
          /\.json$/,
          /\.svg$/
        ],
        loader: 'url-loader',
        query: {
          limit: 10000,
          name: 'static/media/[name].[hash:8].[ext]'
        }
      },
      {
        test: /\.svg$/,
        loader: 'file',
        query: {
          name: 'static/media/[name].[hash:8].[ext]'
        }
      },
    ]
  },
  plugins: [
    new HtmlWebpackPlugin({
      inject: true,
      favicon: paths.appFavicon,
      template: paths.appHtml,
    }),
    new webpack.DefinePlugin(env),
    new webpack.HotModuleReplacementPlugin(),
    new webpack.NamedModulesPlugin(),
  ]
}

//var publicPath = '/';
//var publicUrl = '';
//var env = getClientEnvironment(publicUrl);
//module.exports = {
//devtool: 'cheap-module-source-map',
//entry: [
//require.resolve('react-dev-utils/webpackHotDevClient'),
//require.resolve('./polyfills'),
//paths.appIndexJs
//],
//output: {
//path: paths.appBuild,
//pathinfo: true,
//filename: 'static/js/bundle.js',
//publicPath: publicPath
//},
//resolve: {
//fallback: paths.nodePaths,
//extensions: ['.js', '.json', '.jsx', ''],
//alias: {
//'react-native': 'react-native-web'
//}
//},
//module: {
//preLoaders: [
//{
//test: /\.(js|jsx)$/,
//loader: 'eslint',
//include: paths.appSrc,
//}
//],
//loaders: [
//{
//exclude: [
///\.html$/,
///\.(js|jsx)$/,
///\.css$/,
///\.json$/,
///\.svg$/
//],
//loader: 'url',
//query: {
//limit: 10000,
//name: 'static/media/[name].[hash:8].[ext]'
//}
//},
//{
//test: /\.(js|jsx)$/,
//include: paths.appSrc,
//loader: 'babel',
//query: babelConfig
//},
//{
//test: /\.css$/,
//loader: 'style!css?importLoaders=1!postcss'
//},
//{
//test: /\.json$/,
//loader: 'json',
//exclude: [
//path.resolve(__dirname, '../src/router/routes.json'),
//],
//},
//{
//test: /\.json$/,
//include: [
//path.resolve(__dirname, '../src/router/routes.json'),
//],
//use: [
//{
//loader: 'babel',
//query: babelConfig,
//},
//{
//loader: path.resolve(__dirname, './routes-loader.js'),
//},
//],
//},
//{
//test: /\.svg$/,
//loader: 'file',
//query: {
//name: 'static/media/[name].[hash:8].[ext]'
//}
//}
//]
//},
//postcss: function() {
//return [
//autoprefixer({
//browsers: [
//'>1%',
//'last 4 versions',
//'Firefox ESR',
//'not ie < 9', // React doesn't support IE8 anyway
//]
//}),
//];
//},
//plugins: [
//new InterpolateHtmlPlugin({
//PUBLIC_URL: publicUrl
//}),
//new HtmlWebpackPlugin({
//inject: true,
//template: paths.appHtml,
//}),
//new webpack.DefinePlugin(env),
//new CaseSensitivePathsPlugin(),
//new WatchMissingNodeModulesPlugin(paths.appNodeModules)
//],
//node: {
//fs: 'empty',
//net: 'empty',
//tls: 'empty'
//}
//};
