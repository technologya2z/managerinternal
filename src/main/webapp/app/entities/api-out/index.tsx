import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ApiOut from './api-out';
import ApiOutDetail from './api-out-detail';
import ApiOutUpdate from './api-out-update';
import ApiOutDeleteDialog from './api-out-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ApiOutUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ApiOutUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ApiOutDetail} />
      <ErrorBoundaryRoute path={match.url} component={ApiOut} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={ApiOutDeleteDialog} />
  </>
);

export default Routes;
