import { IHumans } from 'app/shared/model/humans.model';

export interface IJobtitle {
  id?: number;
  name?: string | null;
  code?: string | null;
  description?: string | null;
  applications?: IHumans[] | null;
}

export const defaultValue: Readonly<IJobtitle> = {};
