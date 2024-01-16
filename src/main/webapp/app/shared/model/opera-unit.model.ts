import { IApplication } from 'app/shared/model/application.model';

export interface IOperaUnit {
  id?: number;
  name?: string | null;
  code?: string | null;
  address?: string | null;
  email?: string | null;
  phoneNumber?: string | null;
  owner?: string | null;
  description?: string | null;
  applications?: IApplication[] | null;
}

export const defaultValue: Readonly<IOperaUnit> = {};
