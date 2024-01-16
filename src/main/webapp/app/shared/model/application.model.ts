import dayjs from 'dayjs';
import { ITopicIn } from 'app/shared/model/topic-in.model';
import { ITopicOut } from 'app/shared/model/topic-out.model';
import { IApiIn } from 'app/shared/model/api-in.model';
import { IApiOut } from 'app/shared/model/api-out.model';
import { IApiInfo } from 'app/shared/model/api-info.model';
import { ITopic } from 'app/shared/model/topic.model';
import { IOperaUnit } from 'app/shared/model/opera-unit.model';
import { IDatabaseInfo } from 'app/shared/model/database-info.model';
import { IHumans } from 'app/shared/model/humans.model';
import { ApplicationType } from 'app/shared/model/enumerations/application-type.model';

export interface IApplication {
  id?: number;
  name?: string | null;
  code?: string | null;
  description?: string | null;
  linkSourceRepository?: string | null;
  linkCmRepository?: string | null;
  serverlive?: string | null;
  serveruat?: string | null;
  linkLive?: string | null;
  linkUat?: string | null;
  enviroment?: string | null;
  subComponent?: string | null;
  epicJira?: string | null;
  swaggerLink?: string | null;
  dateStart?: string | null;
  appType?: ApplicationType | null;
  dateGolive?: string | null;
  topicIns?: ITopicIn[] | null;
  topicOuts?: ITopicOut[] | null;
  apiIns?: IApiIn[] | null;
  apiOuts?: IApiOut[] | null;
  apiInfos?: IApiInfo[] | null;
  topics?: ITopic[] | null;
  operaunits?: IOperaUnit[] | null;
  databaseinfos?: IDatabaseInfo[] | null;
  humans?: IHumans[] | null;
}

export const defaultValue: Readonly<IApplication> = {};
