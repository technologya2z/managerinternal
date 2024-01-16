import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import OperaUnit from './opera-unit';
import OperaUnitDetail from './opera-unit-detail';
import OperaUnitUpdate from './opera-unit-update';
import OperaUnitDeleteDialog from './opera-unit-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={OperaUnitUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={OperaUnitUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={OperaUnitDetail} />
      <ErrorBoundaryRoute path={match.url} component={OperaUnit} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={OperaUnitDeleteDialog} />
  </>
);

export default Routes;
