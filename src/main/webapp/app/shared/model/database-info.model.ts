import { IApplication } from 'app/shared/model/application.model';
import { DatabaseType } from 'app/shared/model/enumerations/database-type.model';

export interface IDatabaseInfo {
  id?: number;
  name?: string | null;
  serviceName?: string | null;
  userName?: string | null;
  passWord?: string | null;
  ipServer?: string | null;
  port?: string | null;
  dataType?: DatabaseType | null;
  applications?: IApplication[] | null;
}

export const defaultValue: Readonly<IDatabaseInfo> = {};
