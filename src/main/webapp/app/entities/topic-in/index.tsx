import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import TopicIn from './topic-in';
import TopicInDetail from './topic-in-detail';
import TopicInUpdate from './topic-in-update';
import TopicInDeleteDialog from './topic-in-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={TopicInUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={TopicInUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={TopicInDetail} />
      <ErrorBoundaryRoute path={match.url} component={TopicIn} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={TopicInDeleteDialog} />
  </>
);

export default Routes;
