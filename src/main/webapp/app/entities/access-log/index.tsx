import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import AccessLog from './access-log';
import AccessLogDetail from './access-log-detail';
import AccessLogUpdate from './access-log-update';
import AccessLogDeleteDialog from './access-log-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={AccessLogUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={AccessLogUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={AccessLogDetail} />
      <ErrorBoundaryRoute path={match.url} component={AccessLog} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={AccessLogDeleteDialog} />
  </>
);

export default Routes;
