import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Partner1 from './partner-1';
import Partner1Detail from './partner-1-detail';
import Partner1Update from './partner-1-update';
import Partner1DeleteDialog from './partner-1-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={Partner1Update} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={Partner1Update} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={Partner1Detail} />
      <ErrorBoundaryRoute path={match.url} component={Partner1} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={Partner1DeleteDialog} />
  </>
);

export default Routes;
