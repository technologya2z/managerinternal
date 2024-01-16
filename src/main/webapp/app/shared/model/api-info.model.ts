import dayjs from 'dayjs';
import { IApiIn } from 'app/shared/model/api-in.model';
import { IApiOut } from 'app/shared/model/api-out.model';
import { IApplication } from 'app/shared/model/application.model';

export interface IApiInfo {
  id?: number;
  name?: string | null;
  path?: string | null;
  requestExample?: string | null;
  responseExample?: string | null;
  dateCreate?: string | null;
  description?: string | null;
  method?: string | null;
  apiIns?: IApiIn[] | null;
  apiOuts?: IApiOut[] | null;
  application?: IApplication | null;
  applicationIns?: IApplication | null;
  applicationOuts?: IApplication | null;
}

export const defaultValue: Readonly<IApiInfo> = {};
