import React from 'react';
import { Translate } from 'react-jhipster';

import MenuItem from 'app/shared/layout/menus/menu-item';
import { faMap } from '@fortawesome/free-solid-svg-icons/faMap';
import { faBan } from '@fortawesome/free-solid-svg-icons/faBan';

const EntitiesMenu = () => {
  return (
    <>
      {/* prettier-ignore */}

      <MenuItem icon="map" to="/area" >
        <span className="fontNav">
          <Translate  contentKey="global.menu.entities.area" />
        </span>
      </MenuItem>
      <MenuItem icon="sitemap" to="/organization">
        <span className="fontNav" >
        <Translate contentKey="global.menu.entities.organization" />
          </span>
      </MenuItem>
      <MenuItem icon="people-group" to="/department">
        <span className="fontNav">
          <Translate contentKey="global.menu.entities.department" />
        </span>
      </MenuItem>
      <MenuItem icon="closed-captioning" to="/jobtitle">
       <span className="fontNav">
          <Translate contentKey="global.menu.entities.jobtitle" />
       </span>
      </MenuItem>
      <MenuItem icon="user" to="/humans">
        <span className="fontNav">
          <Translate contentKey="global.menu.entities.humans" />
        </span>
      </MenuItem>
      <MenuItem icon="asterisk" to="/access-log">
       <span className="fontNav">
          Access Log
       </span>
      </MenuItem>
      {/* jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here */}
    </>
  );
};

export default EntitiesMenu as React.ComponentType<any>;
