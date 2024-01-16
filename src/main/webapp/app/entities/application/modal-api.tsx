import React, { useEffect, useState } from 'react';
import { Alert, Button, Col, Form, Modal, ModalBody, ModalFooter, ModalHeader, Row, Table } from 'reactstrap';
import { getSortState, TextFormat, translate, Translate, ValidatedField } from 'react-jhipster';
import { Link } from 'react-router-dom';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';
import { ASC, DESC, ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';
import { overridePaginationStateWithQueryParams } from 'app/shared/util/entity-utils';
import { searchEntitiesApiIn } from 'app/entities/api-in/api-in.reducer';
import { searchEntitiesApiOut } from 'app/entities/api-out/api-out.reducer';

const ModalAPI = ({ props, isOpen, onClose, idApiInfo }) => {
  const dispatch = useAppDispatch();

  const [modalIsOpen, setModalIsOpen] = useState(isOpen);
  const [paginationState, setPaginationState] = useState(
    overridePaginationStateWithQueryParams(getSortState(props.location, ITEMS_PER_PAGE, 'id'), props.location.search)
  );

  const apiOutList = useAppSelector(state => state.apiOut.entities);
  const loadingapiOut = useAppSelector(state => state.apiOut.loading);
  const totalItemsapiOut = useAppSelector(state => state.apiOut.totalItems);

  const apiInList = useAppSelector(state => state.apiIn.entities);
  const loadingapiIn = useAppSelector(state => state.apiIn.loading);
  const totalItemsapiIn = useAppSelector(state => state.apiIn.totalItems);

  const handleClose = () => {
    setModalIsOpen(false);
    onClose && onClose();
  };

  useEffect(() => {
    if (idApiInfo != null) {
      const searchCriterials = [];
      searchCriterials['apiInfoId.equals'] = idApiInfo;
      dispatch(
        searchEntitiesApiIn({
          page: paginationState.activePage - 1,
          size: paginationState.itemsPerPage,
          sort: `${paginationState.sort},${paginationState.order}`,
          searchCriterials,
        })
      );

      dispatch(
        searchEntitiesApiOut({
          page: paginationState.activePage - 1,
          size: paginationState.itemsPerPage,
          sort: `${paginationState.sort},${paginationState.order}`,
          searchCriterials,
        })
      );
    }
  }, [idApiInfo]);

  return (
    <div>
      {isOpen || modalIsOpen ? (
        <Modal size={'lg'} isOpen={true} toggle={handleClose} autoFocus={false} backdrop="static" id="modal-api-page">
          <ModalHeader id="login-title" data-cy="loginTitle" toggle={handleClose}>
            <label>Thông tin API</label>
          </ModalHeader>
          <ModalBody>
            <Row>
              <h4>
                <Translate contentKey="cmspApp.apiIn.home.title">Api Ins</Translate>
              </h4>
              <div className="table-responsive">
                {apiInList && apiInList.length > 0 ? (
                  <Table responsive>
                    <thead>
                      <tr>
                        <th className="hand">
                          <Translate contentKey="cmspApp.apiIn.dateConnect">Date Connect</Translate> <FontAwesomeIcon icon="sort" />
                        </th>
                        <th className="hand">
                          <Translate contentKey="cmspApp.apiIn.description">Description</Translate> <FontAwesomeIcon icon="sort" />
                        </th>
                        <th>
                          <Translate contentKey="cmspApp.apiIn.apiInfo">Api Info</Translate>
                        </th>
                        <th>
                          <Translate contentKey="cmspApp.apiIn.application">Application</Translate>
                        </th>
                        <th />
                      </tr>
                    </thead>
                    <tbody>
                      {apiInList.map((apiIn, i) => (
                        <tr key={`entity-${i}`} data-cy="entityTable">
                          <td>
                            {apiIn.dateConnect ? <TextFormat type="date" value={apiIn.dateConnect} format={APP_LOCAL_DATE_FORMAT} /> : null}
                          </td>
                          <td>{apiIn.description}</td>
                          <td>{apiIn.apiInfo ? <Link to={`/api-info/${apiIn.apiInfo.id}`}>{apiIn.apiInfo.name}</Link> : ''}</td>
                          <td>
                            {apiIn.application ? <Link to={`/application/${apiIn.application.id}`}>{apiIn.application.name}</Link> : ''}
                          </td>
                          <td className="text-end">
                            <div className="btn-group flex-btn-group-container">
                              <Button tag={Link} to={`/api-in/${apiIn.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                                <FontAwesomeIcon icon="eye" />{' '}
                                <span className="d-none d-md-inline">
                                  <Translate contentKey="entity.action.view">View</Translate>
                                </span>
                              </Button>
                            </div>
                          </td>
                        </tr>
                      ))}
                    </tbody>
                  </Table>
                ) : (
                  !loadingapiIn && (
                    <div className="alert alert-warning">
                      <Translate contentKey="cmspApp.apiIn.home.notFound">No Api Ins found</Translate>
                    </div>
                  )
                )}
              </div>
            </Row>

            <Row>
              <h4>
                <Translate contentKey="cmspApp.apiOut.home.title">Api Out</Translate>
              </h4>
              <div className="table-responsive">
                {apiOutList && apiOutList.length > 0 ? (
                  <Table responsive>
                    <thead>
                      <tr>
                        <th className="hand">
                          <Translate contentKey="cmspApp.apiOut.dateConnect">Date Connect</Translate> <FontAwesomeIcon icon="sort" />
                        </th>
                        <th className="hand">
                          <Translate contentKey="cmspApp.apiOut.description">Description</Translate> <FontAwesomeIcon icon="sort" />
                        </th>
                        <th>
                          <Translate contentKey="cmspApp.apiOut.apiInfo">Api Info</Translate> <FontAwesomeIcon icon="sort" />
                        </th>
                        <th>
                          <Translate contentKey="cmspApp.apiOut.application">Application</Translate> <FontAwesomeIcon icon="sort" />
                        </th>
                        <th />
                      </tr>
                    </thead>
                    <tbody>
                      {apiOutList &&
                        apiOutList.map((apiOut, i) => (
                          <tr key={`entity-${i}`} data-cy="entityTable">
                            <td>
                              {apiOut.dateConnect ? (
                                <TextFormat type="date" value={apiOut.dateConnect} format={APP_LOCAL_DATE_FORMAT} />
                              ) : null}
                            </td>
                            <td>{apiOut.description}</td>
                            <td>{apiOut.apiInfo ? <Link to={`/api-info/${apiOut.apiInfo.id}`}>{apiOut.apiInfo.name}</Link> : ''}</td>
                            <td>
                              {apiOut.application ? (
                                <Link to={`/application/${apiOut.application.id}`}>{apiOut.application.name}</Link>
                              ) : (
                                ''
                              )}
                            </td>
                            <td className="text-end">
                              <div className="btn-group flex-btn-group-container">
                                <Button tag={Link} to={`/api-out/${apiOut.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                                  <FontAwesomeIcon icon="eye" />{' '}
                                  <span className="d-none d-md-inline">
                                    <Translate contentKey="entity.action.view">View</Translate>
                                  </span>
                                </Button>
                              </div>
                            </td>
                          </tr>
                        ))}
                    </tbody>
                  </Table>
                ) : (
                  !loadingapiOut && (
                    <div className="alert alert-warning">
                      <Translate contentKey="cmspApp.apiOut.home.notFound">No Api Outs found</Translate>
                    </div>
                  )
                )}
              </div>
            </Row>
          </ModalBody>
        </Modal>
      ) : null}
    </div>
  );
};

export default ModalAPI;
