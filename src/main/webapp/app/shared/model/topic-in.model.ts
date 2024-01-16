import dayjs from 'dayjs';
import { ITopic } from 'app/shared/model/topic.model';
import { IApplication } from 'app/shared/model/application.model';

export interface ITopicIn {
  id?: number;
  dateConnect?: string | null;
  description?: string | null;
  topic?: ITopic | null;
  application?: IApplication | null;
}

export const defaultValue: Readonly<ITopicIn> = {};
