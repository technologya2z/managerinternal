import dayjs from 'dayjs';

export interface IAccessLog {
  id?: number;
  empCode?: string | null;
  empUsername?: string | null;
  empFullName?: string | null;
  accessResource?: string | null;
  description?: string | null;
  ipAddress?: string | null;
  accessTime?: string | null;
}

export const defaultValue: Readonly<IAccessLog> = {};
