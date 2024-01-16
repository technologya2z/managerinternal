import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import TopicOut from './topic-out';
import TopicOutDetail from './topic-out-detail';
import TopicOutUpdate from './topic-out-update';
import TopicOutDeleteDialog from './topic-out-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={TopicOutUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={TopicOutUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={TopicOutDetail} />
      <ErrorBoundaryRoute path={match.url} component={TopicOut} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={TopicOutDeleteDialog} />
  </>
);

export default Routes;
