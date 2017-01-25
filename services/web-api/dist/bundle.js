require("source-map-support").install();
/******/ (function(modules) { // webpackBootstrap
/******/ 	// The module cache
/******/ 	var installedModules = {};
/******/
/******/ 	// The require function
/******/ 	function __webpack_require__(moduleId) {
/******/
/******/ 		// Check if module is in cache
/******/ 		if(installedModules[moduleId])
/******/ 			return installedModules[moduleId].exports;
/******/
/******/ 		// Create a new module (and put it into the cache)
/******/ 		var module = installedModules[moduleId] = {
/******/ 			exports: {},
/******/ 			id: moduleId,
/******/ 			loaded: false
/******/ 		};
/******/
/******/ 		// Execute the module function
/******/ 		modules[moduleId].call(module.exports, module, module.exports, __webpack_require__);
/******/
/******/ 		// Flag the module as loaded
/******/ 		module.loaded = true;
/******/
/******/ 		// Return the exports of the module
/******/ 		return module.exports;
/******/ 	}
/******/
/******/
/******/ 	// expose the modules object (__webpack_modules__)
/******/ 	__webpack_require__.m = modules;
/******/
/******/ 	// expose the module cache
/******/ 	__webpack_require__.c = installedModules;
/******/
/******/ 	// __webpack_public_path__
/******/ 	__webpack_require__.p = "";
/******/
/******/ 	// Load entry module and return exports
/******/ 	return __webpack_require__(0);
/******/ })
/************************************************************************/
/******/ ([
/* 0 */
/***/ function(module, exports, __webpack_require__) {

	'use strict';
	
	Object.defineProperty(exports, "__esModule", {
	  value: true
	});
	exports.server = exports.onStart = undefined;
	
	__webpack_require__(1);
	
	var _app = __webpack_require__(2);
	
	var _app2 = _interopRequireDefault(_app);
	
	function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }
	
	// Set default node environment to development
	process.env.NODE_ENV = process.env.NODE_ENV || 'development';
	
	// Export the application
	exports.default = _app2.default;
	exports.onStart = _app.onStart;
	exports.server = _app.server;

/***/ },
/* 1 */
/***/ function(module, exports) {

	module.exports = require("babel-polyfill");

/***/ },
/* 2 */
/***/ function(module, exports, __webpack_require__) {

	'use strict';
	
	Object.defineProperty(exports, "__esModule", {
	  value: true
	});
	exports.server = exports.onStart = undefined;
	
	var start = function () {
	  var _ref = _asyncToGenerator(regeneratorRuntime.mark(function _callee() {
	    return regeneratorRuntime.wrap(function _callee$(_context) {
	      while (1) {
	        switch (_context.prev = _context.next) {
	          case 0:
	            if (!_environment2.default.seedDB) {
	              _context.next = 4;
	              break;
	            }
	
	            console.log("Seeding!");
	            _context.next = 4;
	            return seed();
	
	          case 4:
	            _context.next = 6;
	            return startServer();
	
	          case 6:
	          case 'end':
	            return _context.stop();
	        }
	      }
	    }, _callee, this);
	  }));
	
	  return function start() {
	    return _ref.apply(this, arguments);
	  };
	}();
	
	var _express = __webpack_require__(3);
	
	var _express2 = _interopRequireDefault(_express);
	
	var _bodyParser = __webpack_require__(4);
	
	var _bodyParser2 = _interopRequireDefault(_bodyParser);
	
	var _http = __webpack_require__(5);
	
	var _http2 = _interopRequireDefault(_http);
	
	var _environment = __webpack_require__(6);
	
	var _environment2 = _interopRequireDefault(_environment);
	
	var _express3 = __webpack_require__(9);
	
	var _express4 = _interopRequireDefault(_express3);
	
	var _routes = __webpack_require__(12);
	
	var _routes2 = _interopRequireDefault(_routes);
	
	function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }
	
	function _asyncToGenerator(fn) { return function () { var gen = fn.apply(this, arguments); return new Promise(function (resolve, reject) { function step(key, arg) { try { var info = gen[key](arg); var value = info.value; } catch (error) { reject(error); return; } if (info.done) { resolve(value); } else { return Promise.resolve(value).then(function (value) { step("next", value); }, function (err) { step("throw", err); }); } } return step("next"); }); }; }
	
	var app = (0, _express2.default)();
	var server = _http2.default.createServer(app);
	(0, _express2.default)(app);
	(0, _express4.default)(app);
	(0, _routes2.default)(app);
	
	// Start the server
	function startServer() {
	  return new Promise(function (resolve) {
	    server.listen(_environment2.default.port, _environment2.default.ip, function () {
	      console.log('Express server listening on %d', _environment2.default.port);
	      resolve();
	    });
	  });
	}
	
	var onStart = start();
	
	// Expose app
	exports.default = app;
	exports.onStart = onStart;
	exports.server = server;

/***/ },
/* 3 */
/***/ function(module, exports) {

	module.exports = require("express");

/***/ },
/* 4 */
/***/ function(module, exports) {

	module.exports = require("body-parser");

/***/ },
/* 5 */
/***/ function(module, exports) {

	module.exports = require("http");

/***/ },
/* 6 */
/***/ function(module, exports, __webpack_require__) {

	/* WEBPACK VAR INJECTION */(function(__dirname) {'use strict';
	
	Object.defineProperty(exports, "__esModule", {
	  value: true
	});
	
	var _path = __webpack_require__(7);
	
	var _path2 = _interopRequireDefault(_path);
	
	var _lodash = __webpack_require__(8);
	
	var _lodash2 = _interopRequireDefault(_lodash);
	
	function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }
	
	//import production from './production.js'
	//import development from './development.js'
	var development = {};
	var production = {};
	
	function requiredProcessEnv(name) {
	  if (!process.env[name]) {
	    throw new Error('You must set the ' + name + ' environment variable');
	  }
	  return process.env[name];
	}
	
	// All configurations will extend these options
	// ============================================
	var all = {
	  env: process.env.NODE_ENV,
	
	  // Root path of server
	  root: _path2.default.normalize(__dirname + '/../..'),
	
	  // Server port
	  port: process.env.PORT || 3001,
	
	  // Server IP
	  ip: process.env.IP || '0.0.0.0',
	
	  // Server base e.g. http://malik.ml.rpi.edu:3001
	  serverURL: process.env.DOMAIN,
	
	  // Grab environment variable for postgresql host
	  PGHOST: process.env.PG_HOST || 'localhost',
	
	  // Secret for session
	  secrets: {}
	
	};
	
	var node_env = process.env.NODE_ENV == 'production' ? production : development;
	
	// Export the config object based on the NODE_ENV
	// ==============================================
	exports.default = _lodash2.default.merge(all, node_env || {});
	/* WEBPACK VAR INJECTION */}.call(exports, "/"))

/***/ },
/* 7 */
/***/ function(module, exports) {

	module.exports = require("path");

/***/ },
/* 8 */
/***/ function(module, exports) {

	module.exports = require("lodash");

/***/ },
/* 9 */
/***/ function(module, exports, __webpack_require__) {

	'use strict';
	
	Object.defineProperty(exports, "__esModule", {
	  value: true
	});
	
	exports.default = function (app) {
	  app.use(_bodyParser2.default.urlencoded({ limit: '5mb', extended: false }));
	  app.use(_bodyParser2.default.json({ limit: '5mb' }));
	  app.use((0, _morgan2.default)('dev'));
	  app.use((0, _cookieParser2.default)());
	};
	
	var _morgan = __webpack_require__(10);
	
	var _morgan2 = _interopRequireDefault(_morgan);
	
	var _bodyParser = __webpack_require__(4);
	
	var _bodyParser2 = _interopRequireDefault(_bodyParser);
	
	var _cookieParser = __webpack_require__(11);
	
	var _cookieParser2 = _interopRequireDefault(_cookieParser);

	function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

/***/ },
/* 10 */
/***/ function(module, exports) {

	module.exports = require("morgan");

/***/ },
/* 11 */
/***/ function(module, exports) {

	module.exports = require("cookie-parser");

/***/ },
/* 12 */
/***/ function(module, exports) {

	'use strict';
	
	Object.defineProperty(exports, "__esModule", {
	  value: true
	});
	
	exports.default = function (app) {
	  app.get('/', function (_, res) {
	    return res.send('telebum server api');
	  });
	
	  app.route('/*').get(function (req, res) {
	    res.status(404).json({ error: 'Invalid route' });
	  });
	};

/***/ }
/******/ ]);
//# sourceMappingURL=bundle.js.map