import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ApiIn from './api-in';
import ApiInDetail from './api-in-detail';
import ApiInUpdate from './api-in-update';
import ApiInDeleteDialog from './api-in-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ApiInUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ApiInUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ApiInDetail} />
      <ErrorBoundaryRoute path={match.url} component={ApiIn} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={ApiInDeleteDialog} />
  </>
);

export default Routes;
