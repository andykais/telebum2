import { createStore, applyMiddleware, compose } from 'redux'
import createLogger from 'redux-logger'

import reducers from './reducers'


export default function configureStore() {
  const middleware = [
    createLogger()
  ]
  const composeEnhancers = window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__ || compose

  const store = createStore(reducers, composeEnhancers(
    applyMiddleware(...middleware)
  ))

  return store
}
