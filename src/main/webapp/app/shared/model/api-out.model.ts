import dayjs from 'dayjs';
import { IApiInfo } from 'app/shared/model/api-info.model';
import { IApplication } from 'app/shared/model/application.model';

export interface IApiOut {
  id?: number;
  description?: string | null;
  dateConnect?: string | null;
  apiInfo?: IApiInfo | null;
  application?: IApplication | null;
}

export const defaultValue: Readonly<IApiOut> = {};
