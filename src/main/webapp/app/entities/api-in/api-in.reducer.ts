import axios from 'axios';
import { createAsyncThunk, isFulfilled, isPending, isRejected } from '@reduxjs/toolkit';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { IQueryParams, createEntitySlice, EntityState, serializeAxiosError } from 'app/shared/reducers/reducer.utils';
import { IApiIn, defaultValue } from 'app/shared/model/api-in.model';

const initialState: EntityState<IApiIn> = {
  loading: false,
  errorMessage: null,
  entities: [],
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false,
};

const apiUrl = 'api/api-ins';

// Actions

export const getEntities = createAsyncThunk('apiIn/fetch_entity_list_api_in', async ({ page, size, sort }: IQueryParams) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}&` : '?'}cacheBuster=${new Date().getTime()}`;
  return axios.get<IApiIn[]>(requestUrl);
});

export const searchEntitiesApiIn = createAsyncThunk(
  'apiIn/fetch_entity_list_api_in',
  async ({ page, size, sort, searchCriterials }: IQueryParams) => {
    let requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}&` : '?'}`;
    Object.keys(searchCriterials).map(function (key) {
      requestUrl += `${key}=${searchCriterials[key]}&`;
    });
    return axios.get<IApiIn[]>(requestUrl);
  }
);

export const getEntity = createAsyncThunk(
  'apiIn/fetch_entity',
  async (id: string | number) => {
    const requestUrl = `${apiUrl}/${id}`;
    return axios.get<IApiIn>(requestUrl);
  },
  { serializeError: serializeAxiosError }
);

export const createEntity = createAsyncThunk(
  'apiIn/create_entity',
  async (entity: IApiIn, thunkAPI) => {
    const result = await axios.post<IApiIn>(apiUrl, cleanEntity(entity));
    thunkAPI.dispatch(getEntities({}));
    return result;
  },
  { serializeError: serializeAxiosError }
);

export const searchEntities = createAsyncThunk(
  'apiIn/fetch_entity_list',
  async ({ page, size, sort, searchCriterials }: IQueryParams) => {
    let requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}&` : '?'}`;
    Object.keys(searchCriterials).map(function (key) {
      requestUrl += `${key}=${searchCriterials[key]}&`;
    });
    return axios.get<IApiIn[]>(requestUrl);
  }
);

export const updateEntity = createAsyncThunk(
  'apiIn/update_entity',
  async (entity: IApiIn, thunkAPI) => {
    const result = await axios.put<IApiIn>(`${apiUrl}/${entity.id}`, cleanEntity(entity));
    thunkAPI.dispatch(getEntities({}));
    return result;
  },
  { serializeError: serializeAxiosError }
);

export const partialUpdateEntity = createAsyncThunk(
  'apiIn/partial_update_entity',
  async (entity: IApiIn, thunkAPI) => {
    const result = await axios.patch<IApiIn>(`${apiUrl}/${entity.id}`, cleanEntity(entity));
    thunkAPI.dispatch(getEntities({}));
    return result;
  },
  { serializeError: serializeAxiosError }
);

export const deleteEntity = createAsyncThunk(
  'apiIn/delete_entity',
  async (id: string | number, thunkAPI) => {
    const requestUrl = `${apiUrl}/${id}`;
    const result = await axios.delete<IApiIn>(requestUrl);
    thunkAPI.dispatch(getEntities({}));
    return result;
  },
  { serializeError: serializeAxiosError }
);

// slice

export const ApiInSlice = createEntitySlice({
  name: 'apiIn',
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
      .addMatcher(isFulfilled(getEntities, searchEntitiesApiIn), (state, action) => {
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
      .addMatcher(isPending(getEntities, getEntity, searchEntitiesApiIn), state => {
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

export const { reset } = ApiInSlice.actions;

// Reducer
export default ApiInSlice.reducer;
