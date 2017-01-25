const path = require('path')
var fs = require('fs')

var appDirectory = fs.realpathSync(process.cwd())
function resolveApp(relativePath) {
  return path.resolve(appDirectory, relativePath)
}


module.exports = {
  appBuild: resolveApp('dist'),
  appSrc: resolveApp('src'),
  appSrcIndex: resolveApp('src/index.js'),
}
