import dayjs from 'dayjs';

export interface IPartner1 {
  id?: number;
  name?: string | null;
  code?: string | null;
  description?: string | null;
}

export const defaultValue: Readonly<IPartner1> = {};
