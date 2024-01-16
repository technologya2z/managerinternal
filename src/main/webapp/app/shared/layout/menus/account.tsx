import React from 'react';
import MenuItem from 'app/shared/layout/menus/menu-item';
import { DropdownItem } from 'reactstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { Translate, translate } from 'react-jhipster';
import { getLoginUrl } from 'app/shared/util/url-utils';
import { NavDropdown } from './menu-components';

const accountMenuItemsAuthenticated = () => (
  <>
    <MenuItem icon="sign-out-alt" to="/logout" data-cy="logout">
      <span className="fontNav">
        <Translate contentKey="global.menu.account.logout">Sign out</Translate>
      </span>
    </MenuItem>
  </>
);

const accountMenuItems = () => (
  <>
    <DropdownItem id="login-item" tag="a" href={getLoginUrl()} data-cy="login">
     <span className="fontNav">
        <FontAwesomeIcon icon="sign-in-alt" /> <Translate contentKey="global.menu.account.login">Sign in</Translate>
     </span>
    </DropdownItem>
  </>
);

export const AccountMenu = ({ isAuthenticated = false, account = null }) => (
  <NavDropdown  icon="user" name={account ? account : 'Tài khoản'} id="account-menu" data-cy="accountMenu">
    {isAuthenticated ? accountMenuItemsAuthenticated() : accountMenuItems()}
  </NavDropdown>
);

export default AccountMenu;
