import axios from 'axios';
import { createAsyncThunk, isFulfilled, isPending, isRejected } from '@reduxjs/toolkit';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { IQueryParams, createEntitySlice, EntityState, serializeAxiosError } from 'app/shared/reducers/reducer.utils';
import { ITopicOut, defaultValue } from 'app/shared/model/topic-out.model';
import { ITopicIn } from 'app/shared/model/topic-in.model';

const initialState: EntityState<ITopicOut> = {
  loading: false,
  errorMessage: null,
  entities: [],
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false,
};

const apiUrl = 'api/topic-outs';
const apiUrlWithId = 'api/topic-out-list';

// Actions

export const getEntities = createAsyncThunk('topicOut/fetch_entity_list', async ({ page, size, sort }: IQueryParams) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}&` : '?'}cacheBuster=${new Date().getTime()}`;
  return axios.get<ITopicOut[]>(requestUrl);
});

export const getEntitiesTopicId = createAsyncThunk(
  'topicIn/fetch_entity_list',
  async (entity: any, thunkAPI) => {
    const result = await axios.post<ITopicIn[]>(apiUrlWithId, entity);
    return result;
  },
  { serializeError: serializeAxiosError }
);

export const searchEntitiesTopicOut = createAsyncThunk(
  'topicOut/fetch_entity_list',
  async ({ page, size, sort, searchCriterials }: IQueryParams) => {
    let requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}&` : '?'}`;
    Object.keys(searchCriterials).map(function (key) {
      requestUrl += `${key}=${searchCriterials[key]}&`;
    });
    return axios.get<ITopicOut[]>(requestUrl);
  }
);

export const getEntity = createAsyncThunk(
  'topicOut/fetch_entity',
  async (id: string | number) => {
    const requestUrl = `${apiUrl}/${id}`;
    return axios.get<ITopicOut>(requestUrl);
  },
  { serializeError: serializeAxiosError }
);

export const createEntity = createAsyncThunk(
  'topicOut/create_entity',
  async (entity: ITopicOut, thunkAPI) => {
    const result = await axios.post<ITopicOut>(apiUrl, cleanEntity(entity));
    thunkAPI.dispatch(getEntities({}));
    return result;
  },
  { serializeError: serializeAxiosError }
);

export const updateEntity = createAsyncThunk(
  'topicOut/update_entity',
  async (entity: ITopicOut, thunkAPI) => {
    const result = await axios.put<ITopicOut>(`${apiUrl}/${entity.id}`, cleanEntity(entity));
    thunkAPI.dispatch(getEntities({}));
    return result;
  },
  { serializeError: serializeAxiosError }
);

export const partialUpdateEntity = createAsyncThunk(
  'topicOut/partial_update_entity',
  async (entity: ITopicOut, thunkAPI) => {
    const result = await axios.patch<ITopicOut>(`${apiUrl}/${entity.id}`, cleanEntity(entity));
    thunkAPI.dispatch(getEntities({}));
    return result;
  },
  { serializeError: serializeAxiosError }
);

export const deleteEntity = createAsyncThunk(
  'topicOut/delete_entity',
  async (id: string | number, thunkAPI) => {
    const requestUrl = `${apiUrl}/${id}`;
    const result = await axios.delete<ITopicOut>(requestUrl);
    thunkAPI.dispatch(getEntities({}));
    return result;
  },
  { serializeError: serializeAxiosError }
);

// slice

export const TopicOutSlice = createEntitySlice({
  name: 'topicOut',
  initialState,
  extraReducers(builder) {
    builder
      .addCase(getEntity.fulfilled, (state, action) => {
        state.loading = false;
        state.entity = action.payload.data;
      })
      .addCase(deleteEntity.fulfilled, state => {
        state.updating = false;
        state.updateSuccess = true;
        state.entity = {};
      })
      .addMatcher(isFulfilled(getEntities, getEntitiesTopicId, searchEntitiesTopicOut), (state, action) => {
        const { data, headers } = action.payload;

        return {
          ...state,
          loading: false,
          entities: data,
          totalItems: parseInt(headers['x-total-count'], 10),
        };
      })
      .addMatcher(isFulfilled(createEntity, updateEntity, partialUpdateEntity), (state, action) => {
        state.updating = false;
        state.loading = false;
        state.updateSuccess = true;
        state.entity = action.payload.data;
      })
      .addMatcher(isPending(getEntities, getEntitiesTopicId, searchEntitiesTopicOut, getEntity), state => {
        state.errorMessage = null;
        state.updateSuccess = false;
        state.loading = true;
      })
      .addMatcher(isPending(createEntity, updateEntity, partialUpdateEntity, deleteEntity), state => {
        state.errorMessage = null;
        state.updateSuccess = false;
        state.updating = true;
      });
  },
});

export const { reset } = TopicOutSlice.actions;

// Reducer
export default TopicOutSlice.reducer;
