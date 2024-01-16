import { IHumans } from 'app/shared/model/humans.model';
import { IOrganization } from 'app/shared/model/organization.model';

export interface IDepartment {
  id?: number;
  name?: string | null;
  code?: string | null;
  description?: string | null;
  humans?: IHumans[] | null;
  organization?: IOrganization | null;
}

export const defaultValue: Readonly<IDepartment> = {};
