import './home.scss';

import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import { Translate } from 'react-jhipster';
import { Row, Col, Alert } from 'reactstrap';

import { getLoginUrl, REDIRECT_URL } from 'app/shared/util/url-utils';
import { useAppSelector } from 'app/config/store';
import Axios from 'axios';

export const Home = () => {
  const account = useAppSelector(state => state.authentication.account);

  const [totalHumans, setTotalHumans] = useState(0);
  const [totalApplications, setTotalApplications] = useState(0);
  const [totalApplicationRunning, setTotalApplicationRunning] = useState(0);
  const [totalDatabases, setTotalDatabases] = useState(0);
  const [totalDatabaseRunning, setTotalDatabaseRunning] = useState(0);
  const [totalApi, setTotalApi] = useState(0);
  const [totalApirunning, setTotalApirunning] = useState(0);

  useEffect(() => {
    const requestUrl = `api/public/dashboard`;

    Axios.get(`${requestUrl}`).then(res => {
      const data = res.data;
      setTotalHumans(data.totalHumans);
      setTotalApplications(data.totalApplications);
      setTotalApplicationRunning(data.totalApplicationRunning);
      setTotalDatabases(data.totalDatabases);
      setTotalDatabaseRunning(data.totalDatabaseRunning);
      setTotalApi(data.totalApi);
      setTotalApirunning(data.totalApirunning);
    });
  }, []);

  useEffect(() => {
    const redirectURL = localStorage.getItem(REDIRECT_URL);
    if (redirectURL) {
      localStorage.removeItem(REDIRECT_URL);
      location.href = `${location.origin}${redirectURL}`;
    }
  });

  return (
    <Row>
      <Col md="12">
        {setTotalHumans ? (
          <div>
            <div className="row clearfix mt-2">
              <div className="col-lg-3 col-md-6 col-sm-12">
                <div className="card">
                  <div className="card-body">
                    <div className="widgets2">
                      <div className="state">
                        <h6>Nhân sự</h6>
                        <h2>{totalHumans}</h2>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <div className="col-lg-3 col-md-6 col-sm-12">
                <div className="card">
                  <div className="card-body">
                    <div className="widgets2">
                      <div className="state">
                        <h6>Ứng dụng</h6>
                        <h2>{totalApplications}</h2>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <div className="col-lg-3 col-md-6 col-sm-12">
                <div className="card">
                  <div className="card-body">
                    <div className="widgets2">
                      <div className="state">
                        <h6>Database</h6>
                        <h2>{totalDatabases}</h2>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <div className="col-lg-3 col-md-6 col-sm-12">
                <div className="card">
                  <div className="card-body">
                    <div className="widgets2">
                      <div className="state">
                        <h6>API</h6>
                        <h2>{totalApi}</h2>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div className="row clearfix mt-4"></div>
          </div>
        ) : null}
      </Col>
    </Row>
  );
};

export default Home;
