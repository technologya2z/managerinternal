import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import DatabaseInfo from './database-info';
import DatabaseInfoDetail from './database-info-detail';
import DatabaseInfoUpdate from './database-info-update';
import DatabaseInfoDeleteDialog from './database-info-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={DatabaseInfoUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={DatabaseInfoUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={DatabaseInfoDetail} />
      <ErrorBoundaryRoute path={match.url} component={DatabaseInfo} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={DatabaseInfoDeleteDialog} />
  </>
);

export default Routes;
