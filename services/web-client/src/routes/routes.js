import React from 'react'
import { Route, Switch } from 'react-router-dom'

import App from 'containers/App'
import Home from 'containers/Home'
import NotFound from 'containers/NotFound'

const Routes = (props) =>
  <App>
    <Switch>
      <Route path='/' exact component={Home}/>
      <Route path='/test' render={() => <div>test</div>}/>
      <Route component={NotFound}/>
    </Switch>
  </App>

export default Routes
