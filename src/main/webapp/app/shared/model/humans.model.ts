import { IApplication } from 'app/shared/model/application.model';
import { IJobtitle } from 'app/shared/model/jobtitle.model';
import { IDepartment } from 'app/shared/model/department.model';

export interface IHumans {
  id?: number;
  fullName?: string | null;
  code?: string | null;
  userName?: string | null;
  email?: string | null;
  phoneNumber?: string | null;
  dateOfBirth?: string | null;
  homePhome?: string | null;
  address?: string | null;
  wards?: string | null;
  district?: string | null;
  province?: string | null;
  joinDate?: string | null;
  married?: string | null;
  gender?: string | null;
  description?: string | null;
  humanType?: string | null;
  jobtitleName?: string | null;
  applications?: IApplication[] | null;
  jobtitles?: IJobtitle[] | null;
  department?: IDepartment | null;
}

export const defaultValue: Readonly<IHumans> = {};
