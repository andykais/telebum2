import React, { Component } from 'react'
import { connect } from 'react-redux'
import createBrowserHistory from 'history/createBrowserHistory'
import { Router } from 'react-router'
import { Route, Link } from 'react-router-dom'

import { changeLocation } from './ducks'
import App from '../containers/App'

const history = createBrowserHistory()

class ReduxRouter extends Component {

  handleLocationChange(location, action) {
    const { dispatch } = this.props

    dispatch(changeLocation(location))
  }

  componentDidMount() {
    this.unsubscribe = history.listen(this.handleLocationChange.bind(this))
  }

  componentWillUnmount() {
    this.unsubscribe()
  }

  render() {
    return (
      <Router history={history}>
      <div>
      <li><Link to='/test'>Test</Link></li>
      <li><Link to='/'>home </Link></li>
      <Route exact path='/' component={App}/>
      <Route path='/test' render={() => <div>test page</div>}/>
      </div>
      </Router>
    )

  }
}

export const { push, replace, go, goBack, goForward } = history
export default connect()(ReduxRouter)
