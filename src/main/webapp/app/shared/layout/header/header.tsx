import './header.scss';

import React, {useState} from 'react';
import {Storage, Translate} from 'react-jhipster';
import LoadingBar from 'react-redux-loading-bar';
import {Collapse, Nav, Navbar, NavbarBrand, NavbarToggler, NavItem, NavLink} from 'reactstrap';
import {useAppDispatch, useAppSelector} from 'app/config/store';
import ApplicationMenu from 'app/entities/menuApplication';
import MenuTopic from 'app/entities/menuTopic';
import AdminMenu from 'app/entities/adminMenu';
import {setLocale} from 'app/shared/reducers/locale';
import {AccountMenu, EntitiesMenu} from '../menus';
import {Brand, Home} from './header-components';
import MenuItem from "app/shared/layout/menus/menu-item";
import {NavDropdown} from "app/shared/layout/menus/menu-components";
import {NavLink as Link} from "react-router-dom";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";

export interface IHeaderProps {
  isAuthenticated: boolean;
  isAdmin: boolean;
  ribbonEnv: string;
  isInProduction: boolean;
  isOpenAPIEnabled: boolean;
  currentLocale: string;
}

const Header = (props: IHeaderProps) => {
  const [menuOpen, setMenuOpen] = useState(false);
  const accountLogin = useAppSelector(state => state.authentication.account);
  const dispatch = useAppDispatch();

  const handleLocaleChange = event => {
    const langKey = event.target.value;
    Storage.session.set('locale', langKey);
    dispatch(setLocale(langKey));
  };

  const renderDevRibbon = () =>
    props.isInProduction === false ? (
      <div className="ribbon dev">
        <a href="">
          <Translate contentKey={`global.ribbon.${props.ribbonEnv}`}/>
        </a>
      </div>
    ) : null;

  const toggleMenu = () => setMenuOpen(!menuOpen);

  /* jhipster-needle-add-element-to-menu - JHipster will add new menu items here */

  return (
    <div id="app-header">
      {renderDevRibbon()}
      <LoadingBar className="loading-bar"/>
      <Navbar data-cy="navbar" dark expand="md" fixed="top" className="jh-navbar">
        <NavbarToggler aria-label="Menu" onClick={toggleMenu}/>
        <Brand/>
        <Collapse isOpen={menuOpen} navbar>
          <Nav id="header-tabs" className="ms-auto" navbar>
            <Home/>
            {props.isAdmin &&
            <NavItem>
              <NavLink tag={Link} to="/search" className="d-flex align-items-center">
                <FontAwesomeIcon icon="search"/>
                <span className="fontNav">
                Tìm kiếm
                </span>
              </NavLink>
            </NavItem>
            }
            {props.isAdmin && <EntitiesMenu/>}
            {props.isAdmin && <ApplicationMenu/>}
            {props.isAdmin && <MenuTopic/>}
            {props.isAdmin && <AdminMenu/>}
            {/* {props.isAdmin && <AdminMenuItems  />} */}
            {/* <AdminMenu showOpenAPI={undefined}/> */}
            <AccountMenu isAuthenticated={props.isAuthenticated} account={accountLogin.login}/>
          </Nav>
        </Collapse>
      </Navbar>
    </div>
  );
};

export default Header;
