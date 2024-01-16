import React from 'react';
import { Switch } from 'react-router-dom';
import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Area from './area';
import Organization from './organization';
import Department from './department';
import Jobtitle from './jobtitle';
import Humans from './humans';
import Application from './application';
import ApiInfo from './api-info';
import ApiIn from './api-in';
import ApiOut from './api-out';
import DatabaseInfo from './database-info';
import OperaUnit from './opera-unit';
import Topic from './topic';
import TopicIn from './topic-in';
import TopicOut from './topic-out';
import Partner1 from './partner-1';
import AccessLog from './access-log';
import ApplicationSearch from "app/entities/application/application-search";
/* jhipster-needle-add-route-import - JHipster will add routes here */

export default ({ match }) => {
  return (
    <div>
      <Switch>
        {/* prettier-ignore */}
        <ErrorBoundaryRoute path={`${match.url}area`} component={Area} />
        <ErrorBoundaryRoute path={`${match.url}organization`} component={Organization} />
        <ErrorBoundaryRoute path={`${match.url}department`} component={Department} />
        <ErrorBoundaryRoute path={`${match.url}jobtitle`} component={Jobtitle} />
        <ErrorBoundaryRoute path={`${match.url}humans`} component={Humans} />
        <ErrorBoundaryRoute path={`${match.url}application`} component={Application} />
        <ErrorBoundaryRoute path={`${match.url}api-info`} component={ApiInfo} />
        <ErrorBoundaryRoute path={`${match.url}api-in`} component={ApiIn} />
        <ErrorBoundaryRoute path={`${match.url}api-out`} component={ApiOut} />
        <ErrorBoundaryRoute path={`${match.url}database-info`} component={DatabaseInfo} />
        <ErrorBoundaryRoute path={`${match.url}opera-unit`} component={OperaUnit} />
        <ErrorBoundaryRoute path={`${match.url}topic`} component={Topic} />
        <ErrorBoundaryRoute path={`${match.url}topic-in`} component={TopicIn} />
        <ErrorBoundaryRoute path={`${match.url}topic-out`} component={TopicOut} />
        <ErrorBoundaryRoute path={`${match.url}search`} component={ApplicationSearch} />
        <ErrorBoundaryRoute path={`${match.url}partner`} component={Partner1} />
        <ErrorBoundaryRoute path={`${match.url}access-log`} component={AccessLog} />
        {/* jhipster-needle-add-route-path - JHipster will add routes here */}
      </Switch>
    </div>
  );
};
