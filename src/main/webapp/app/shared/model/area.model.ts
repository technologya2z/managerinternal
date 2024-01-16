import { IOrganization } from 'app/shared/model/organization.model';

export interface IArea {
  id?: number;
  name?: string | null;
  code?: string | null;
  description?: string | null;
  organizations?: IOrganization[] | null;
}

export const defaultValue: Readonly<IArea> = {};
