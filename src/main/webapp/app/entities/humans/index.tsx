import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Humans from './humans';
import HumansDetail from './humans-detail';
import HumansUpdate from './humans-update';
import HumansDeleteDialog from './humans-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={HumansUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={HumansUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={HumansDetail} />
      <ErrorBoundaryRoute path={match.url} component={Humans} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={HumansDeleteDialog} />
  </>
);

export default Routes;
