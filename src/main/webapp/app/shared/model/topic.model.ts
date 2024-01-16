import { ITopicIn } from 'app/shared/model/topic-in.model';
import { ITopicOut } from 'app/shared/model/topic-out.model';
import { IApplication } from 'app/shared/model/application.model';

export interface ITopic {
  id?: number;
  name?: string | null;
  enviroment?: string | null;
  bootrapServer?: string | null;
  locationTopic?: string | null;
  rootproducer?: string | null;
  rootTable?: string | null;
  message?: string | null;
  linkDoc?: string | null;
  topicIns?: ITopicIn[] | null;
  topicOuts?: ITopicOut[] | null;
  applications?: IApplication[] | null;
}

export const defaultValue: Readonly<ITopic> = {};
