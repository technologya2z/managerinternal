import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ApiInfo from './api-info';
import ApiInfoDetail from './api-info-detail';
import ApiInfoUpdate from './api-info-update';
import ApiInfoDeleteDialog from './api-info-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ApiInfoUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ApiInfoUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ApiInfoDetail} />
      <ErrorBoundaryRoute path={match.url} component={ApiInfo} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={ApiInfoDeleteDialog} />
  </>
);

export default Routes;
