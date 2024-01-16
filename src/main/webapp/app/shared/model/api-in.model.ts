import dayjs from 'dayjs';
import { IApiInfo } from 'app/shared/model/api-info.model';
import { IApplication } from 'app/shared/model/application.model';

export interface IApiIn {
  id?: number;
  dateConnect?: string | null;
  description?: string | null;
  apiInfo?: IApiInfo | null;
  application?: IApplication | null;
}

export const defaultValue: Readonly<IApiIn> = {};
