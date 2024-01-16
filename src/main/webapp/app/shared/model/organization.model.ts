import { IDepartment } from 'app/shared/model/department.model';
import { IArea } from 'app/shared/model/area.model';

export interface IOrganization {
  id?: number;
  name?: string | null;
  code?: string | null;
  description?: string | null;
  address?: string | null;
  phoneNumber?: string | null;
  departments?: IDepartment[] | null;
  area?: IArea | null;
}

export const defaultValue: Readonly<IOrganization> = {};
