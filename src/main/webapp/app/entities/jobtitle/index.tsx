import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Jobtitle from './jobtitle';
import JobtitleDetail from './jobtitle-detail';
import JobtitleUpdate from './jobtitle-update';
import JobtitleDeleteDialog from './jobtitle-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={JobtitleUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={JobtitleUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={JobtitleDetail} />
      <ErrorBoundaryRoute path={match.url} component={Jobtitle} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={JobtitleDeleteDialog} />
  </>
);

export default Routes;
